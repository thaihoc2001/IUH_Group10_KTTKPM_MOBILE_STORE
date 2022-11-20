import { ApiProperty } from "@nestjs/swagger";

export class OrderFilters {

    @ApiProperty({
        required: false
    })
    searchText: string;

    @ApiProperty({
        required: false
    })
    status: string;

    @ApiProperty({
        default: 0,
    })
    page: number;
    @ApiProperty({
        default: 20
    })
    size: number;
    @ApiProperty({
        description: "fieldname,asc|desc",
        required: false,
        isArray: true
    })
    sort: [string];
}

export class OrderByUserFilters {

    @ApiProperty({
        required: false
    })
    status: string;

    @ApiProperty({
        default: 0,
    })
    page: number;
    @ApiProperty({
        default: 20
    })
    size: number;
    @ApiProperty({
        description: "fieldname,asc|desc",
        required: false,
        isArray: true
    })
    sort: [string];
}

export class createOrderRequestModel {
    @ApiProperty()
    coupon: number;

    @ApiProperty()
    typePayment: string;

    @ApiProperty()
    note: string;
}