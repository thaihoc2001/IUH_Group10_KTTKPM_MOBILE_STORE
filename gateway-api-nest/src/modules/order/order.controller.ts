import { Body, Controller, Get, HttpStatus,Param,Post,Query, Req, Res, UseGuards } from "@nestjs/common";
import { ApiBearerAuth, ApiOkResponse, ApiResponse, ApiTags } from "@nestjs/swagger";
import { Request, Response } from "express";
import { createOrderRequestModel, OrderByUserFilters, OrderFilters, ResponseModel } from "src/dto";
import { AuthService } from "../auth/auth.service";
import { OrderService } from "./order.service";

@ApiBearerAuth()
@ApiTags('api/orders')
@Controller('api/orders')
export class OrderController {
    constructor(private authService: AuthService, private orderService: OrderService){}

    @ApiResponse({
        type: ResponseModel,
        isArray: false
    })
    @Get('')
    async getAll(@Req() req: Request, @Res() res: Response, @Query() filters: OrderFilters) {
        const token = req.headers.authorization.split(" ")[1];
        const auth = await this.authService.authentication(token);
        const data = await this.orderService.getOrders(auth.data.userId, filters);
        return res.status(data.status).json(data.data);
    }

    @ApiResponse({
        type: ResponseModel,
        isArray: false
    })
    @Post('')
    async createOrder(@Req() req: Request, @Res() res: Response, @Body() order: createOrderRequestModel) {
        const token = req.headers.authorization.split(" ")[1];
        const auth = await this.authService.authentication(token);
        const data = await this.orderService.createOrder(auth.data.userId, order);
        return res.status(data.status).json(data.data);
    }

    @ApiResponse({
        type: ResponseModel,
        isArray: false
    })
    @Get('/user/me')
    async getAllByUser(@Req() req: Request, @Res() res: Response, @Query() filters: OrderByUserFilters) {
        const token = req.headers.authorization.split(" ")[1];
        const auth = await this.authService.authentication(token);
        const data = await this.orderService.getOrdersByUser(auth.data.userId, filters);
        return res.status(data.status).json(data.data);
    }

    @ApiResponse({
        type: ResponseModel,
        isArray: false
    })
    @Get(':id')
    async getById(@Req() req: Request, @Res() res: Response, @Param('id') id: string) {
        const token = req.headers.authorization.split(" ")[1];
        const auth = await this.authService.authentication(token);
        const data = await this.orderService.getOrderById(auth.data.userId, id);
        return res.status(data.status).json(data.data);
    }
}