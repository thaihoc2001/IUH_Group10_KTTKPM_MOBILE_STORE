import { HttpModule } from '@nestjs/axios';
import { Module } from '@nestjs/common';
import { AuthService } from '../auth/auth.service';
import { ImageController } from './image.controller';
import { ImageService } from './image.service';

@Module({
    imports: [
        HttpModule.register({
            timeout: 5000,
            maxRedirects: 5,
        }),
    ],
    controllers: [ImageController],
    providers: [AuthService, ImageService],
    exports: []
})
export class ImageModule {}