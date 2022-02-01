export class Cliente{
    post<T>(apiUrl: string, checkin: import("../checkin/checkin").Checkin): import("rxjs").Observable<import("../checkin/checkin").Checkin> {
        throw new Error("Method not implemented.");
    }
    id: number;
    nome: String;
    cpf: String;
    telefone: String;
    dataCadastro: String;
}
