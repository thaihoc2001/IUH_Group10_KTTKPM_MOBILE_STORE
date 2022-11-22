import { Body, CacheInterceptor, CacheKey, CacheTTL, CACHE_MANAGER, Controller, Delete, Get, Inject, Param, Post, Put, Query, Req, Res, UseInterceptors } from "@nestjs/common";
import { ApiBearerAuth, ApiResponse, ApiTags } from "@nestjs/swagger";
import { Request, Response } from "express";
import { CreateProductRequestModel, ProductFilters, ResponseModel } from "src/dto";
import { AuthService } from "../auth/auth.service";
import { ProductService } from "./product.service";
import { Cache } from "cache-manager";
import { SkipThrottle, Throttle } from "@nestjs/throttler";


@ApiBearerAuth()
@ApiTags('api/products')
@Controller('api/products')
export class ProductController {
    constructor(private authService: AuthService, private productService: ProductService, @Inject(CACHE_MANAGER) private cacheManager: Cache) {}

    @ApiResponse({
        type: ResponseModel,
        isArray: false
    })
    @Post('')
    async createProduct(@Req() req: Request, @Res() res: Response, @Body() product: CreateProductRequestModel) {
        const token = req.headers.authorization.split(" ")[1];
        const auth = await this.authService.authentication(token);
        const data = await this.productService.createProduct(auth.data.userId, product);
        return res.status(data.status).json(data.data);
    }

    @ApiResponse({
        type: ResponseModel,
        isArray: false
    })
    @Get('')
    async getAllProduct(@Req() req: Request, @Res() res: Response, @Query() filters: ProductFilters) {
        const products = await this.cacheManager.get(
            "product_key"
        );
        
        if (products) return res.status(200).json(products);
        const data = await this.productService.getAllProduct(filters);
        this.cacheManager.set("product_key", data.data, 6000);
        return res.status(data.status).json(data.data);
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
        return res.status(data.status).json(data.data);
    }

    @Get(':id')
    async getProductById(@Req() req: Request, @Res() res: Response, @Param('id') id: string) {
        const product = await this.cacheManager.get(
            id.toString()
        );
        if (product) return res.status(200).json(product);
        const data = await this.productService.getByProductId(id);
        this.cacheManager.set(id.toString(), data.data, 6000);
        return res.status(data.status).json(data.data);
    }

    @Delete(':id')
    async deleteProductById(@Req() req: Request, @Res() res: Response, @Param('id') id: string) {
        const token = req.headers.authorization.split(" ")[1];
        const auth = await this.authService.authentication(token);
        const data = await this.productService.deleteProductById(auth.data.userId,id);
        return res.status(data.status).json(data.data);
    }
}