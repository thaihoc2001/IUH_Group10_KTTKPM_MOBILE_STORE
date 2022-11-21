const express = require('express');
const router = express.Router();
const { AuthenticationController } = require('../controllers');
const { AuthMiddleware } = require('../middelware');
router.post('/register', AuthenticationController.register);
router.post('/login', AuthenticationController.login);
router.post('/authentication', AuthMiddleware.isAuthenticated, AuthenticationController.authentication);
router.post('/refresh-token', AuthenticationController.refreshToken);

module.exports = router;

