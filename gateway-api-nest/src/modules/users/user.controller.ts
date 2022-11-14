import { Controller, Get, HttpStatus,Query, Req, Res, UseGuards } from "@nestjs/common";
import { ApiBearerAuth, ApiOkResponse, ApiResponse, ApiTags } from "@nestjs/swagger";
import { Request, Response } from "express";
import { Observable } from "rxjs";

import { UserService } from "./user.service";

@ApiBearerAuth()
@ApiTags('api/users')
@Controller('api/users')
export class UserController {
    constructor(private userService: UserService){}

    @Get('/me')
    async getUserDeltail(@Req() req: Request, @Res() res: Response) {
        const token = req.headers.authorization;
        console.log(token);
        console.log("dsdsdsdsds");
        
        
        const data =  await this.userService.findUserByUserId();
        return res.status(200).json(data.data);
    }
}