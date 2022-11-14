const express = require('express');
const router = express.Router();
const { AuthMiddleware } = require('../middelware');

const {UserController} = require('../controllers');

router.get('/:userId', UserController.getUser);
router.put('/:userId', AuthMiddleware.isAuthenticated, UserController.updateUser);

module.exports = router;

