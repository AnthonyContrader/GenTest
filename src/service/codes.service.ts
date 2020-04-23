import { Observable  } from 'rxjs';
import {AbstractService} from './abstractservice';
import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {CodesDTO} from '../dto/codesdto';
import {UserDTO} from "../dto/userdto";
@Injectable({
  providedIn: 'root'
})

export class CodesService extends AbstractService<CodesDTO>{

  constructor(http: HttpClient) {
    super(http);
    this.microservicetype = "codes";
    this.name= "gentest";
    this.port= "8080";
  }

  auth() {
    const user = JSON.parse(localStorage.getItem('currentUser')) as UserDTO;
    if (user) {
      return 'Bearer ' + user.authorities;
    } else {
      return '';
    }
  }

    insertcodes(dto: CodesDTO): Observable<any> {
      return this.http.post('http://localhost:' + this.port + '/' + this.type + '/insertcodes', dto);
  }

    uploadcontent(fileString: string): Observable<any> {
      return this.http.post('http://localhost:' + this.port + '/' + this.type + '/uploadcontent', fileString);
  }

    saveName(nome: string): Observable<any> {
    return this.http.post('http://localhost:' + this.port + '/' + this.type + '/setnamep', nome);
  }

}
