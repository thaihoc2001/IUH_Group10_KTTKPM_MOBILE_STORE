import { HttpModule } from '@nestjs/axios';
import { Module } from '@nestjs/common';
import { AuthService } from '../auth/auth.service';
import { CartController } from './cart.controller';
import { CartService } from './cart.service';

@Module({
    imports: [
        HttpModule.register({
            timeout: 5000,
            maxRedirects: 5,
        }),
    ],
    controllers: [CartController],
    providers: [CartService, AuthService],
    exports: []
})
export class CartModule {}