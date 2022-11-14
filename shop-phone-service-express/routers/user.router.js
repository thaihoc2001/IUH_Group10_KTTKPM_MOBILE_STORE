const express = require('express');
const router = express.Router();
const { AuthMiddleware } = require('../middelware');

const {UserController} = require('../controllers');

router.get('/me', AuthMiddleware.isAuthenticated, UserController.getMe);
router.get('/:userId', UserController.getUser);
router.put('/:userId', AuthMiddleware.isAuthenticated, UserController.updateUser);

module.exports = router;

