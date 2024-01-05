export class UploadDto {
    constructor(limit:number,counter:number,sessionId:string) {
        this.limit=limit;
        this.counter=counter;
        this.sessionId=sessionId;
    }

    limit:number;
    counter:number;
    sessionId:string;

}
