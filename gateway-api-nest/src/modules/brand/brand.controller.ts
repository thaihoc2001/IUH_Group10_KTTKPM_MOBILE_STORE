import { Body, Controller, Get, HttpStatus,Post,Query, Req, Res, UseGuards } from "@nestjs/common";
import { ApiBearerAuth, ApiOkResponse, ApiResponse, ApiTags } from "@nestjs/swagger";
import { Request, Response } from "express";
import { ResponseModel } from "src/dto";
import { BrandFilters, CreateBrandRequestModel } from "src/dto/brand.model";
import { AuthService } from "../auth/auth.service";
import { BrandService } from "./brand.service";

@ApiBearerAuth()
@ApiTags('api/brands')
@Controller('api/brands')
export class BrandController {

    constructor(private brandService: BrandService, private authService: AuthService){}

    @ApiResponse({
        type: ResponseModel,
        isArray: false
    })
    @Post('')
    async create(@Req() req: Request, @Res() res: Response, @Body() brand: CreateBrandRequestModel) {
        const token = req.headers.authorization.split(" ")[1];
        const auth = await this.authService.authentication(token);
        const data = await this.brandService.createBrand(auth.data.userId, brand);
        return res.status(data.status).json(data.data);
    }

    @ApiResponse({
        type: ResponseModel,
        isArray: false
    })
    @Get('')
    async getAll(@Req() req: Request, @Res() res: Response, @Query() filters: BrandFilters) {
        const data = await this.brandService.getAll(filters);
        return res.status(data.status).json(data.data);
    }

}