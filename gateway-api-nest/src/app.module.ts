import { MiddlewareConsumer, Module, RequestMethod } from '@nestjs/common';
import { AuthenticationMiddleware } from './middleware/authentication.middleware';
import { AuthModule } from './modules/auth/auth.module';
import { BrandModule } from './modules/brand/brand.module';
import { CartModule } from './modules/cart/cart.module';
import { ImageModule } from './modules/image/image.module';
import { OrderModule } from './modules/order/order.module';
import { ProductModule } from './modules/product/product.module';
import { UserModule } from './modules/users/user.module';

@Module({
  imports: [UserModule, AuthModule, ProductModule, ImageModule, OrderModule, CartModule, BrandModule],
  controllers: [],
  providers: [],
})
export class AppModule {
  configure(consumer: MiddlewareConsumer) {
    consumer
      .apply(AuthenticationMiddleware)
      .forRoutes({ path: 'api/products', method: RequestMethod.POST });
  }
}
