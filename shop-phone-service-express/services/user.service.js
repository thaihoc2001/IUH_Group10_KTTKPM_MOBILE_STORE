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

const updateUser = async (user_id, user) => {
    try {
        if (user_id && user) {
            const options = {
                where: { id: user_id }
            }
            const updatedUser = await Users.update(user, options);
            return updatedUser;
        } else {
            throw Error("User not found!");
        }
    } catch (error) {
        throw error;
    }
}
getAllUsers = async () => {
    try {
        const users = await Users.findAll();
        return users;
    } catch (error) {
        throw error;
    }
}

module.exports = {
    getUserById,
    createUser,
    getUserByUsername,
    updateUser,
    getAllUsers
}

