import { HttpModule } from '@nestjs/axios';
import { CacheInterceptor, Module } from '@nestjs/common';
import { APP_GUARD, APP_INTERCEPTOR } from '@nestjs/core';
import { ThrottlerGuard } from '@nestjs/throttler';
import { AuthService } from '../auth/auth.service';
import { ProductController } from './product.controller';
import { ProductService } from './product.service';

@Module({
    imports: [
        HttpModule.register({
            timeout: 5000,
            maxRedirects: 5,
        }),
    ],
    controllers: [ProductController],
    providers: [ProductService, AuthService,
        {
            provide: APP_INTERCEPTOR,
            useClass: CacheInterceptor,
        },
        {
            provide: APP_GUARD,
            useClass: ThrottlerGuard
        }
    ],
    exports: []
})
export class ProductModule {}