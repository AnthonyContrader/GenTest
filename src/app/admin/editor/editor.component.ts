import { Component, OnInit, ViewChild } from '@angular/core';
import 'brace';
import 'brace/mode/java';
import 'brace/theme/eclipse';

@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.css']
})
export class EditorComponent {
  @ViewChild('editor') editor;
  text = '';

  ngAfterViewInit() {
    this.editor.setTheme('eclipse');
    this.editor.setMode('java');
    this.editor.getEditor().setOptions({
        enableBasicAutocompletion: true
    });
  }
}
