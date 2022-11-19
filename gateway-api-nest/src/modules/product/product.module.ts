import { HttpModule } from '@nestjs/axios';
import { Module } from '@nestjs/common';
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
    providers: [ProductService, AuthService],
    exports: []
})
export class ProductModule {}