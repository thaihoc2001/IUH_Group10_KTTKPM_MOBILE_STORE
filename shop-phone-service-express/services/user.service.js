const { Users, Roles } = require('../models');

const getUserById = async (userId) => {
    const options = {
        where: { id: userId }
    }
    const user = await Users.findOne(options);
    return user;
}

const getUserByUsername = async (username) => {
    const options = {
        where: { username: username }
    }
    const user = await Users.findOne(options);
    return user;
}

const createUser = async (user) => {
    try {
        if (user) {
            const newUser = await Users.create(user);
            return newUser;
        } else {
            throw Error("User not found!");
        }
    } catch (error) {
        throw error;
    }
}


module.exports = {
    getUserById,
    createUser,
    getUserByUsername
}

