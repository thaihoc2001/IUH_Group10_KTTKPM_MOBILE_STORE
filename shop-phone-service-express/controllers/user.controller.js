const { UserService } = require('../services');

const getUser = async (req, res) => {
    try {
        const {userId} = req.params;
        const user = await UserService.getUserById(userId);
        return res.status(200).json(user);
    } catch(error) {
        console.log(error);
        return res.status(500).json({message: "Internal Server Error!"});
    }
}

const updateUser = async (req, res) => {
    try {
        const {userId} = req.params;
        const {email, first_name, last_name, phone} = req.body;
        const updatedUser = await UserService.updateUser(userId, {
            email,
            first_name,
            last_name,
            phone,
        });
        const newUser = await UserService.getUserById(userId);
        return res.status(200).json(newUser);
    } catch(error) {
        console.log(error);
        return res.status(500).json({message: "Internal Server Error!"});
    }
}

module.exports = {
    getUser,
    updateUser
}