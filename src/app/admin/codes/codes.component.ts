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

  uploadDocument(file) {
    this.file = file;
    let fileReader = new FileReader();
    fileReader.onloadend = (e) => {
      //console.log(myReader.result);
      // Entire file
      console.log(fileReader.result);

      this.fileString = fileReader.result;
    };

    fileReader.readAsText(this.file);
  }
  readf(){
    this.editor.setText(this.fileString)
  }

  uintToString(uintArray) {
    var encodedString = String.fromCharCode.apply(null, uintArray);
    return decodeURIComponent(escape(encodedString));
  }
  ngAfterViewInit() {
    this.editor.setTheme('eclipse');
    this.editor.setMode('java');
    this.editor.resize();
    this.editor.getEditor().setOptions({
      enableBasicAutocompletion: true
    });
  }
  fileChanged(e) {
    this.file = e.target.files[0];
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
    this.service.insertcodes(codes).subscribe(() => this.getCodes())
  }

  clear(){
    this.codestoinsert = new  CodesDTO();
  }
}
