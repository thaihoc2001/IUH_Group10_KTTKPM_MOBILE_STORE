const {Users, Account} = require('../model');
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');
const config = require('../config');

const isAuthenticated = async (req, res, next) => {
    try {
        const access_token = req.headers["authorization"].split(" ")[1];
        const user = jwt.verify(access_token, config.AUTH_TOKEN_SECRET.ACCESS_TOKEN);
        if (user){
            req.user = user;
            return next();
        }
        return res.status(401).json({message: 'User not exist!'});
    }catch (err) {
        return res.status(401).json(err);
    }
}

module.exports = {
    isAuthenticated
}
