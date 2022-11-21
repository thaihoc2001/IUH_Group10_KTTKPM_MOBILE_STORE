import { Controller, Get, HttpStatus,Param,Query, Req, Res, UseGuards } from "@nestjs/common";
import { ApiBearerAuth, ApiOkResponse, ApiResponse, ApiTags } from "@nestjs/swagger";
import { Request, Response } from "express";
import { Observable } from "rxjs";
import { ResponseModel } from "src/dto";

import { UserService } from "./user.service";

@ApiBearerAuth()
@ApiTags('api/users')
@Controller('api/users')
export class UserController {
    constructor(private userService: UserService){}

    @ApiResponse({
        type: ResponseModel,
        isArray: false
    })
    @Get(':id')
    async getUserDeltail(@Req() req: Request, @Res() res: Response, @Param('id') id: string) {
        const token = req.headers.authorization;
        const data =  await this.userService.findUserByUserId(token,id);
        return res.status(data.status).json(data.data);
    }
    
    @ApiResponse({
        type: ResponseModel,
        isArray: false
    })
    @Get('')
    async getUserAll(@Req() req: Request, @Res() res: Response) {
        const token = req.headers.authorization;
        const data =  await this.userService.getAll(token);
        return res.status(data.status).json(data.data);
    }

    @ApiResponse({
        type: ResponseModel,
        isArray: false
    })
    @Get('/user/profile')
    async getProfile(@Req() req: Request, @Res() res: Response) {
        const token = req.headers.authorization;
        const data =  await this.userService.getProfile(token);
        return res.status(data.status).json(data.data);
    }
}