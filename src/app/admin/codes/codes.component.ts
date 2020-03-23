import {Component, OnInit, ViewChild} from '@angular/core';
import {CodesDTO} from '../../../dto/codesdto';
import {CodesService} from '../../../service/codes.service';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-codes',
  templateUrl: './codes.component.html',
  styleUrls: ['./codes.component.css']
})
export class CodesComponent implements OnInit {

  codess: CodesDTO[];
  codestoinsert: CodesDTO = new CodesDTO();
  @ViewChild('editor') editor;
  file: any;
  text: string

  constructor(private service: CodesService, private http: HttpClient) {
  }
  fileString: any = "";


  ngAfterViewInit() {

    this.editor.setTheme('eclipse');
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
  }

  getCodes(){
    this.service.getAll().subscribe(codess => this.codess = codess);
  }

  delete(codes: CodesDTO){
    this.service.delete(codes.id).subscribe(() => this.getCodes())
  }

  update(codes: CodesDTO){
    this.service.update(codes).subscribe(() => this.getCodes())
  }

  insertcodes(codes: CodesDTO){
    this.service.insertcodes(codes).subscribe(() => this.getCodes());
  }

  uploadcontent(fileString: string){
    this.service.uploadcontent(fileString).subscribe(() => this.getCodes())
  }

  clear(){
    this.codestoinsert = new  CodesDTO();
  }
}
