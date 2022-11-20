import { Body, Controller, Get, HttpStatus,Post,Query, Req, Res, UseGuards } from "@nestjs/common";
import { ApiBearerAuth, ApiOkResponse, ApiResponse, ApiTags } from "@nestjs/swagger";
import { Request, Response } from "express";
import { CartDetailAddRequestModel, CartDetailRemoveRequestModel, ResponseModel } from "../../dto";
import { AuthService } from "../auth/auth.service";
import { CartService } from "./cart.service";

@ApiBearerAuth()
@ApiTags('api/carts')
@Controller('api/carts')
export class CartController {
    constructor(private authService: AuthService, private cartService: CartService){}

    @ApiResponse({
        type: ResponseModel,
        isArray: false
    })
    @Get('')
    async getCart(@Req() req: Request, @Res() res: Response) {
        const token = req.headers.authorization.split(" ")[1];
        const auth = await this.authService.authentication(token);
        const data = await this.cartService.getCartByUser(auth.data.userId);
        return res.status(data.status).json(data.data);
    }

    @ApiResponse({
        type: ResponseModel,
        isArray: false
    })
    @Post('')
    async CreateCart(@Req() req: Request, @Res() res: Response) {
        const token = req.headers.authorization.split(" ")[1];
        const auth = await this.authService.authentication(token);
        const data = await this.cartService.createCart(auth.data.userId);
        return res.status(data.status).json(data.data);
    }

    @ApiResponse({
        type: ResponseModel,
        isArray: false
    })
    @Post('/cart-detail/add')
    async addToCart(@Req() req: Request, @Res() res: Response, @Body() requestModel: CartDetailAddRequestModel) {
        const token = req.headers.authorization.split(" ")[1];
        const auth = await this.authService.authentication(token);
        const data = await this.cartService.addToCart(auth.data.userId, requestModel);
        return res.status(data.status).json(data.data);
    }

    @ApiResponse({
        type: ResponseModel,
        isArray: false
    })
    @Post('/cart-detail/remove')
    async removeToCart(@Req() req: Request, @Res() res: Response, @Body() requestModel: CartDetailRemoveRequestModel) {
        const token = req.headers.authorization.split(" ")[1];
        const auth = await this.authService.authentication(token);
        const data = await this.cartService.removeToCart(auth.data.userId, requestModel);
        return res.status(data.status).json(data.data);
    }
}