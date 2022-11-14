const {UserService} = require('../services');

const getUser = async (req, res) => {
    try {
        const {userId} = req.params;
        console.log('call');
        const user = await UserService.getUserById(userId);
        return res.status(200).json(user);
    } catch(error) {
        console.log(error);
        return res.status(500).json({message: "Internal Server Error!"});
    }
}

module.exports = {
    getUser
}