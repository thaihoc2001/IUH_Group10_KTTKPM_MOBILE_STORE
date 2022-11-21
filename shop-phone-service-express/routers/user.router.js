const express = require('express');
const router = express.Router();
const { AuthMiddleware } = require('../middelware');

const {UserController} = require('../controllers');

router.get('/', AuthMiddleware.isAuthenticated, UserController.getAllUsers);
router.get('/user/me', AuthMiddleware.isAuthenticated, UserController.getMe);
router.get('/:userId', UserController.getUser);
router.put('/:userId', AuthMiddleware.isAuthenticated, UserController.updateUser);

module.exports = router;

