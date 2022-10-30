'use strict';
const {Model} = require('sequelize');

module.exports = (sequelize, DataTypes) => {
    class Users extends Model{}
    Users.init({
        id: {
            type: DataTypes.UUID,
            primaryKey: true
        },
        email: {
            type: DataTypes.STRING,
        },
        first_name: {
            type: DataTypes.STRING
        },
        is_deleted: {
            type: DataTypes.BOOLEAN,
        },
        last_name: {
            type: DataTypes.STRING
        },
        password: {
            type: DataTypes.STRING
        },
        phone: {
            type: DataTypes.STRING
        },
        username: {
            type: DataTypes.STRING
        },
        role_id:{
            type: DataTypes.UUID
        }
    }, {
        sequelize,
        tableName: 'users',
        underscored: true,
        createdAt: 'created_at',
        updatedAt: 'updated_at'
    });
    return Users;
}