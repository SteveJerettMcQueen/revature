export class ReimbursementData {
    eruid: any;
    amount: number;
    description: string;
    status: {
        statusid: number;
        status: string;
    };
    type: { typeid: number; };
    receipt: any;
}
