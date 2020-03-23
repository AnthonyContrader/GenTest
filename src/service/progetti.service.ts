import { Injectable } from '@angular/core';
import {AbstractService} from './abstractservice';
import {ProgettiDTO} from 'src/dto/progettidto';
import {UserDTO} from 'src/dto/userdto';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ProgettiService extends AbstractService<ProgettiDTO> {

  constructor(http: HttpClient) { 

    super(http);
    this.type = 'progetti';
  }
  getAllById(userDTO: UserDTO): Observable<ProgettiDTO[]>{
    return this.http.post<ProgettiDTO[]>('http://localhost:8080/'+this.type+'/getallbyuser',userDTO) ;
  }
  /*insertprogetti(progettiDTO: ProgettiDTO): Observable<any>{
    return this.http.post('http://localhost:8080/'+this.type+'/insertprogetti',progettiDTO);
  }*/
}
