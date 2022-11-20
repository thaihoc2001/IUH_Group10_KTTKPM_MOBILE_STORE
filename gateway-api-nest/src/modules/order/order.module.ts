import { HttpModule } from '@nestjs/axios';
import { Module } from '@nestjs/common';
import { AuthService } from '../auth/auth.service';
import { OrderController } from './order.controller';
import { OrderService } from './order.service';

@Module({
    imports: [
        HttpModule.register({
            timeout: 5000,
            maxRedirects: 5,
        }),
    ],
    controllers: [OrderController],
    providers: [OrderService, AuthService],
    exports: []
})
export class OrderModule {}