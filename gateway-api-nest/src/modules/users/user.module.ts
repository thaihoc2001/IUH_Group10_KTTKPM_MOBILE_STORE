import { HttpModule } from '@nestjs/axios';
import { Module } from '@nestjs/common';
import { APP_GUARD } from '@nestjs/core';
import { ThrottlerGuard } from '@nestjs/throttler';
import { UserController } from './user.controller';
import { UserService } from './user.service';

@Module({
    imports: [
        HttpModule.register({
            timeout: 5000,
            maxRedirects: 5,
        }),
    ],
    controllers: [UserController],
    providers: [UserService,
        {
            provide: APP_GUARD,
            useClass: ThrottlerGuard
        }
    ],
    exports: []
})
export class UserModule {}