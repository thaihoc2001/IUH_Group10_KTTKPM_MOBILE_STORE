import { Body, Controller, Get, HttpStatus,Post,Query, Req, Res, UploadedFile, UseGuards, UseInterceptors } from "@nestjs/common";
import { ApiBearerAuth, ApiBody, ApiConsumes, ApiOkResponse, ApiResponse, ApiTags } from "@nestjs/swagger";
import { FileInterceptor } from "@nestjs/platform-express";
import { Request, Response } from "express";
import { ResponseModel } from "src/dto";
import { AuthService } from "../auth/auth.service";
import { ImageService } from "./image.service";

@ApiBearerAuth()
@ApiTags('api/files')
@Controller('api/files')
export class ImageController {
    constructor(private authService: AuthService, private imageService: ImageService){}

    @ApiResponse({
        type: ResponseModel,
        isArray: false
    })
    @ApiConsumes('multipart/form-data')
    @ApiBody({
    schema: {
        type: 'object',
        properties: {
                file: {
                    type: 'string',
                    format: 'binary',
                },
                type: {
                    type: 'string',
                    format: 'string'
                },
                productId: {
                    type: 'string',
                    format: 'string'
                }
            },
        },
    })
    @Post('')
    @UseInterceptors(FileInterceptor('file'))
    async createProduct(@Req() req: Request, @Res() res: Response, @Body() body, @UploadedFile() file?: Express.Multer.File) {
        const token = req.headers.authorization.split(" ")[1];
        const auth = await this.authService.authentication(token);
        const data = await this.imageService.createImage(auth.data.userId, file, body.productId, body.type);
        return res.status(data.status).json(data.data);
    }
}