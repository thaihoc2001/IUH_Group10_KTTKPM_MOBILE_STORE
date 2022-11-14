const { UserService } = require('../services');
const bcrypt = require('bcryptjs');
const register = async (req, res) => {
    try {
        const { email, first_name, is_deleted = false, last_name, password, phone, username, role_id } = req.body;
        const hashPassword = await bcrypt.hash(password,10);
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
        return res.status(200).json(newUser);
    } catch (error) {
        console.log(error);
        return res.status(500).json({ message: "Internal Server Error!" });
    }
}

module.exports = {
    register
}