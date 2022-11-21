import { HttpModule } from '@nestjs/axios';
import { CacheInterceptor, Module } from '@nestjs/common';
import { APP_INTERCEPTOR } from '@nestjs/core';
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
    providers: [AuthService, BrandService,
        {
            provide: APP_INTERCEPTOR,
            useClass: CacheInterceptor,
        },
    ],
    exports: []
})
export class BrandModule {}