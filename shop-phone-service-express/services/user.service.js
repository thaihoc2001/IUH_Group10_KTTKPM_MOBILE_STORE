const { Users, Roles } = require('../models');

const getUserById = async (userId) => {
    const options = {
        where: { id: userId }
    }
    const user = await Users.findOne(options);
    return user;
}

module.exports = {
    getUserById
}

