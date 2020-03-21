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
    this.type = 'codes';
  }
  insertcodes(dto: CodesDTO): Observable<any> {
    return this.http.post('http://localhost:' + this.port + '/' + this.type + '/insertcodes', dto);
  }

}
