import { Body, Controller, Get, Param, Post, Put, Query, Req, Res } from "@nestjs/common";
import { ApiBearerAuth, ApiResponse, ApiTags } from "@nestjs/swagger";
import { Request, Response } from "express";
import { CreateProductRequestModel, ProductFilters, ResponseModel } from "src/dto";
import { AuthService } from "../auth/auth.service";
import { ProductService } from "./product.service";


@ApiBearerAuth()
@ApiTags('api/products')
@Controller('api/products')
export class ProductController {
    constructor(private authService: AuthService, private productService: ProductService) {}

    @ApiResponse({
        type: ResponseModel,
        isArray: false
    })
    @Post('')
    async createProduct(@Req() req: Request, @Res() res: Response, @Body() product: CreateProductRequestModel) {
        const token = req.headers.authorization.split(" ")[1];
        const auth = await this.authService.authentication(token);
        const data = await this.productService.createProduct(auth.data.userId, product);
        return res.status(200).json(data.data);
    }

    @ApiResponse({
        type: ResponseModel,
        isArray: false
    })
    @Get('')
    async getAllProduct(@Req() req: Request, @Res() res: Response, @Query() filters: ProductFilters) {
        const data = await this.productService.getAllProduct(filters);
        return res.status(200).json(data.data);
    }

    @ApiResponse({
        type: ResponseModel,
        isArray: false
    })
    @Put('')
    async updateProduct(@Req() req: Request, @Res() res: Response, @Body() product: CreateProductRequestModel) {
        const token = req.headers.authorization.split(" ")[1];
        const auth = await this.authService.authentication(token);
        const data = await this.productService.updateProduct(auth.data.userId, product);
        return res.status(200).json(data.data);
    }

    @Get(':id')
    async getProductById(@Req() req: Request, @Res() res: Response, @Param('id') id: string) {
        const data = await this.productService.getByProductId(id);
        return res.status(200).json(data.data);
    }

}