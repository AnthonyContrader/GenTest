import { Observable  } from 'rxjs';
import {AbstractService} from './abstractservice';
import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {CodesDTO} from '../dto/codesdto';
@Injectable({
  providedIn: 'root'
})

export class CodesService extends AbstractService<CodesDTO>{

  constructor(http: HttpClient) {
    super(http);
    this.microservicetype = "api";
    this.name= "codes";
    
    
  }
  auth() {
    const codes = JSON.parse(localStorage.getItem('currentCodes')) as CodesDTO;
    if (codes) {
      return 'Bearer ' + codes.authorities;
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
