import { ApiProperty } from "@nestjs/swagger";

export class CartDetailRemoveRequestModel {

    @ApiProperty()
    cartDetailId: string;
}

export class CartDetailAddRequestModel {
    
    @ApiProperty()
    productId: string;

    @ApiProperty()
    quantity: number;
}