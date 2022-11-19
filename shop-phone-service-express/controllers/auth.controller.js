const { UserService } = require('../services');
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');
const config = require('../config');

const register = async (req, res) => {
    try {
        const { email, first_name, is_deleted = false, last_name, password, phone, username, role_id } = req.body;
        const user = UserService.getUserByUsername(username);
        if (user) {
            return res.status(409).json({message: "username exists!"});
        }
        const hashPassword = await bcrypt.hash(password, 10);
        const newUser = await UserService.createUser({
            email,
            first_name,
            is_deleted,
            last_name,
            password: hashPassword,
            phone,
            username,
            role_id
        });
        return res.status(200).json({success: true});
    } catch (error) {
        console.log(error);
        return res.status(500).json({ message: "Internal Server Error!" });
    }
}

const login = async (req, res) => {
    try {
        console.log(req.body);
        const { username, password } = req.body;
        if (!username || !password) {
            return res.status(400).json({ success: false, message: 'Incorrect login details' });
        }
        const user = await UserService.getUserByUsername(username);
        if (!user) {
            return res.status(400).json({ message: 'User not exits!' });
        }
        let isCorrectPass = await bcrypt.compare(password, user.password);
        if (!isCorrectPass) {
            return res.status(400).json({ message: 'Incorrect password' });
        }
        const accessToken = signAccessToken(user.id, user.role_id);
        return res.status(200).json({ accessToken: accessToken });
    } catch (err) {
        console.log(err);
        return res.status(500).json({ message: "Internal Server Error!" });
    }
}

const authentication = (req, res) => {
    try {
        const user = req.user;
        return res.status(200).json({userId: user.id});
    }catch(error) {
        console.log(error);
        return res.status(500).json({ message: "Internal Server Error!" });
    }
}

const signAccessToken = (user_id, role_id) => {
    return jwt.sign(
        {
            id: user_id,
            role_id: role_id
        },
        config.AUTH_TOKEN_SECRET.ACCESS_TOKEN,
    );
}
module.exports = {
    register,
    login,
    authentication
}