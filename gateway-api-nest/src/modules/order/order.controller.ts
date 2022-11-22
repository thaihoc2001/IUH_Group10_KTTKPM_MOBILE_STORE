import { Body, CACHE_MANAGER, Controller, Get, Inject,Param,Post,Query, Req, Res } from "@nestjs/common";
import { ApiBearerAuth, ApiResponse, ApiTags } from "@nestjs/swagger";
import { Request, Response } from "express";
import { createOrderRequestModel, OrderByUserFilters, OrderFilters, ResponseModel } from "src/dto";
import { AuthService } from "../auth/auth.service";
import { OrderService } from "./order.service";
import { Cache } from "cache-manager";

@ApiBearerAuth()
@ApiTags('api/orders')
@Controller('api/orders')
export class OrderController {
    constructor(private authService: AuthService, private orderService: OrderService,  @Inject(CACHE_MANAGER) private cacheManager: Cache){}

    @ApiResponse({
        type: ResponseModel,
        isArray: false
    })
    @Get('')
    async getAll(@Req() req: Request, @Res() res: Response, @Query() filters: OrderFilters) {
        const orders = await this.cacheManager.get(
            "order_key"
        );
        
        if (orders) return res.status(200).json(orders);

        const token = req.headers.authorization.split(" ")[1];
        const auth = await this.authService.authentication(token);
        const data = await this.orderService.getOrders(auth.data.userId, filters);
        this.cacheManager.set("order_key", data.data, 6000);
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
        const orders = await this.cacheManager.get(
            `order_${auth.data.userId}_key`
        );
        
        if (orders) return res.status(200).json(orders);
        const data = await this.orderService.getOrdersByUser(auth.data.userId, filters);
        this.cacheManager.set(`order_${auth.data.userId}_key`, data.data, 6000);
        return res.status(data.status).json(data.data);
    }

    @ApiResponse({
        type: ResponseModel,
        isArray: false
    })
    @Get(':id')
    async getById(@Req() req: Request, @Res() res: Response, @Param('id') id: string) {
        const order = await this.cacheManager.get(
            `order_${id}`
        );
        
        if (order) return res.status(200).json(order);
        const token = req.headers.authorization.split(" ")[1];
        const auth = await this.authService.authentication(token);
        const data = await this.orderService.getOrderById(auth.data.userId, id);
        this.cacheManager.set(`order_${id}`, data.data, 6000);
        return res.status(data.status).json(data.data);
    }
}