import { HttpModule } from '@nestjs/axios';
import { Module } from '@nestjs/common';
import { AuthService } from '../auth/auth.service';
import { BrandController } from './brand.controller';
import { BrandService } from './brand.service';


@Module({
    imports: [
        HttpModule.register({
            timeout: 5000,
            maxRedirects: 5,
        }),
    ],
    controllers: [BrandController],
    providers: [AuthService, BrandService],
    exports: []
})
export class BrandModule {}