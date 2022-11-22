import { CacheModule, MiddlewareConsumer, Module, RequestMethod } from '@nestjs/common';
import { AuthenticationMiddleware } from './middleware/authentication.middleware';
import { AuthModule } from './modules/auth/auth.module';
import { BrandModule } from './modules/brand/brand.module';
import { CartModule } from './modules/cart/cart.module';
import { ImageModule } from './modules/image/image.module';
import { OrderModule } from './modules/order/order.module';
import { ProductModule } from './modules/product/product.module';
import { UserModule } from './modules/users/user.module';
import type { RedisClientOptions } from "redis";
import { ThrottlerModule } from '@nestjs/throttler';

@Module({
  imports: [
    UserModule, 
    AuthModule, 
    ProductModule, 
    ImageModule, 
    OrderModule, 
    CartModule, 
    BrandModule,
    CacheModule.register<RedisClientOptions>({
      isGlobal: true,
      database: 0,
      password: 'r3d1sp4ssw0rd',
      url: "redis://localhost:6379",
      ttl: 6,
      store: 'a',
      
    }),
    ThrottlerModule.forRoot({
      ttl: 10,
      limit: 5,
    }),
  ],
  controllers: [],
  providers: [],
})
export class AppModule {
  configure(consumer: MiddlewareConsumer) {
    consumer
      .apply(AuthenticationMiddleware)
      .forRoutes(
        { path: 'api/products', method: RequestMethod.POST },
        { path: 'api/carts', method: RequestMethod.ALL},
        { path: 'api/users', method: RequestMethod.ALL},
        { path: 'api/orders', method: RequestMethod.ALL}
      );
  }
}
