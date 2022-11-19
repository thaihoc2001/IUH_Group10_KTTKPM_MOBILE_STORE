'use strict';
const {Model} = require('sequelize');

module.exports = (sequelize, DataTypes) => {
    class Roles extends Model{}
    Roles.init({
        id: {
            type: DataTypes.UUID,
            primaryKey: true,
            defaultValue: DataTypes.UUIDV4
        },
        name: {
            type: DataTypes.STRING
        }
        
    }, {
        sequelize,
        tableName: 'roles',
        underscored: true
    });
    Roles.associate = (models) => {
        Roles.hasMany(models.Users, {
            as: 'users',
            foreignKey: 'role_id'
        });
    }
    return Roles;
}