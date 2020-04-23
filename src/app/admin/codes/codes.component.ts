import {Component, OnInit, ViewChild} from '@angular/core';
import {CodesDTO} from '../../../dto/codesdto';
import {CodesService} from '../../../service/codes.service';
import {HttpClient} from '@angular/common/http';
import {ProgettiDTO} from "../../../dto/progettidto";
import {ProgettiService} from "../../../service/progetti.service";
import {formatDate} from "@angular/common";

@Component({
  selector: 'app-codes',
  templateUrl: './codes.component.html',
  styleUrls: ['./codes.component.scss']
})
export class CodesComponent implements OnInit {

  codess: CodesDTO[];
  progetti: ProgettiDTO[];
  codestoinsert: CodesDTO = new CodesDTO()
  @ViewChild('editor') editor;
  fileString: any = "";
  date = new Date();
  file: any;
  text: string;
  data_i: string;
  data_m: string;
  constructor(private service: CodesService, private http: HttpClient, private servicep: ProgettiService) {
  }

  ngAfterViewInit() {

    this.editor.setTheme('monokai');
    this.editor.setMode('java');
    this.editor.resize();
    this.editor.getEditor().setOptions({
      enableBasicAutocompletion: true
    });
  }

  uploadDocument(text, nome: string) {
      this.setnamep(nome);
      this.uploadcontent(text);
  }
  setnamep(nome: string){
    this.service.saveName(nome).subscribe(() => this.getCodes());
  }

  readf(){
    this.editor.setText(this.fileString);
  }

  savetxt(text: string){
    this.uploadcontent(text);
  }

  fileChanged(e, nome: string) {
    this.file = e.target.files[0];
    let fileReader = new FileReader();
    fileReader.onloadend = (e) => {
      this.fileString = fileReader.result;
      this.setnamep(nome);
      this.uploadcontent(this.fileString);
    };
    fileReader.readAsText(this.file);
  }

  ngOnInit() {
    this.getCodes();
    this.getProgetti();
  }

  getCodes(){
    this.service.getAll().subscribe(codess => this.codess = codess);
  }

  getProgetti(){
    this.servicep.getAll().subscribe(progetti => this.progetti = progetti);
  }

  delete(codes: CodesDTO){
    this.service.delete(codes.id).subscribe(() => this.getCodes())
  }

  update(codes: CodesDTO){
    this.service.update(codes).subscribe(() => this.getCodes())
  }

  insert(codes: CodesDTO){
    this.data_i = formatDate(this.date, 'dd-MM-yyyy hh:mm:ss a', 'en-US', );
    this.data_m = formatDate(this.date, 'dd-MM-yyyy hh:mm:ss a', 'en-US', );
    codes.data_i = this.data_i;
    codes.data_m = this.data_m;
    this.service.insert(codes).subscribe(() => this.getCodes());
  }

  uploadcontent(fileString: string){
    this.service.uploadcontent(fileString).subscribe(() => this.getCodes())
  }

  clear(){
    this.codestoinsert = new  CodesDTO();
  }
}
