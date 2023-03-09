include "emu8086.inc"
; You may customize this and other start-up templates;
; The location of this template is c:\emu8086\inc\0_com_template.txt

org 100h

DEFINE_SCAN_NUM
DEFINE_PRINT_STRING
DEFINE_GET_STRING
DEFINE_CLEAR_SCREEN
DEFINE_PRINT_NUM_UNS  

MOV BX, OFFSET var1
CALL IMPRIME_STRING 

PUSH DX
MOV DI, OFFSET substring
MOV DX, 32
CALL GET_STRING 
POP DX

CALL pula_linha 

MOV BX, OFFSET var2
CALL IMPRIME_STRING 

PUSH DX
MOV DI, OFFSET string
MOV DX, 32
CALL GET_STRING
POP DX

CALL pula_linha

PROCURAR:
PUSH DI
PUSH SI
PUSH CX
MOV DI, offset string
PROCURAR_M:
MOV SI, offset substring
MOV CH, [SI]
MOV CL, [DI]
CMP CH, CL
JE PROCURAR_PALAVRA
CMP [DI], 0
JE RESPOSTA2
INC DI
JMP PROCURAR_M
PROCURAR_PALAVRA:
MOV CH, [SI]
MOV CL, [DI]
CMP CH, CL
JE IGUAL
CMP [SI], 0
JE RESPOSTA1
INC DI
JMP PROCURAR_M
IGUAL:
CMP [SI], 0
JE RESPOSTA1
INC SI
INC DI
JMP PROCURAR_PALAVRA

PULA_LINHA:                       
PUSHF
PUSH AX
PUSH DX
MOV AH,2  ; socorro, destruiram ax
MOV DL,13          
INT 21H
MOV AH,2  ; socorro, destruiram ax
MOV DL,10
INT 21H
POP DX          
POP AX
POPF
RET 

IMPRIME_STRING: 
    pushf
    push ax 
    push bx
    push dx
BUSCA_IMPRIME_STRING:  
  
    MOV DL,[BX] 
    CMP DL,0   
    JE  SAIDA_IMPRIME_STRING
    MOV AH,2
    INT 21H 
    INC BX
    JMP BUSCA_IMPRIME_STRING
SAIDA_IMPRIME_STRING:
    pop dx 
    pop bx
    pop ax
    popf
    RET           

RESPOSTA1:
PUSH BX
MOV BX, OFFSET possui
CALL IMPRIME_STRING                
POP BX
MOV AH, 4CH
MOV AL, 0
INT 21H

RESPOSTA2:
PUSH BX
MOV BX, OFFSET naopossui
CALL IMPRIME_STRING
POP BX
MOV AH, 4CH
MOV AL, 0
INT 21H

var1 db "Entre com uma substring:   " ,0
var2 db "Entre com uma string:  " ,0
substring db 33 dup(" ") ,0
string db 33 dup(" ") ,0
possui db "Contem MIGUEL" ,0
naopossui db "Nao contem MIGUEL" ,0







