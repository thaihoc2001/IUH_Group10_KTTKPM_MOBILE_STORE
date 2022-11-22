import { Body, Controller, Post, Req, Res } from "@nestjs/common";
import { ApiBearerAuth, ApiResponse, ApiTags } from "@nestjs/swagger";
import { Request, Response } from "express";
import { LoginRequestModel, RefreshTokenModel, RegisterRequestModel, ResponseModel } from "src/dto";
import { AuthService } from "./auth.service";


@ApiBearerAuth()
@ApiTags('api/auth')
@Controller('api/auth')
export class AuthController {
    constructor(private authService: AuthService) {}

    @ApiResponse({
        type: ResponseModel,
        isArray: false
    })
    @Post('/login')
    async login(@Req() req: Request, @Res() res: Response, @Body() body: LoginRequestModel) {
        const data = await this.authService.login(body);
        return res.status(data.status).json(data.data);
    }

    @ApiResponse({
        type: ResponseModel,
        isArray: false
    })
    @Post('/register')
    async register(@Req() req: Request, @Res() res: Response, @Body() body: RegisterRequestModel) {
        const data = await this.authService.register(body);
        return res.status(data.status).json(data.data);
    }

    @ApiResponse({
        type: ResponseModel,
        isArray: false
    })
    @Post('/refresh-token')
    async refreshToken(@Req() req: Request, @Res() res: Response, @Body() body: RefreshTokenModel) {
        const data = await this.authService.refreshToken(body);
        return res.status(data.status).json(data.data);
    }
}