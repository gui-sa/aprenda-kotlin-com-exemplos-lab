// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)
// Made in KOTLIN PLAYGROUND

enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

class Usuario (val nome:String, val cpf:Long){
    // Poderia ter colocado so o cpf no construtor primario e criado um construtor secundario para os demais parametros... Dai usario o data class sem problemas.
    override fun hashCode():Int{
        return this.cpf.hashCode()
    }
    
    override fun equals(other: Any?):Boolean{
        if (other == null){
            return false
        }
        if (!(other is Usuario)){
            return false
        }
        if( other === this){
            return true
        }
        return this.cpf == other.cpf
    }
    
    override fun toString():String{
        return "$nome $cpf"
    }
    
}

data class ConteudoEducacional(val nome: String, var duracao: Int = 60)




data class Formacao(val nome: String, val nivel:Nivel = Nivel.BASICO, val conteudos: List<ConteudoEducacional>) {
  	init{
		this.checkDuplicate()
    }
    
	@Throws(IllegalArgumentException::class)
    fun checkDuplicate(){
        if(this.conteudos.distinct() != this.conteudos){
            throw IllegalArgumentException("Conteudo Educacional duplicado na Formacao")
        } 
    }
    
    val inscritos = mutableSetOf<Usuario>() // nao faz sentido ter dois usuarios iguais na mesma lista e ordem não importa
   
    
    fun matricular(vararg usuarios: Usuario) {
        for(usuario in usuarios){
            this.inscritos.add(usuario)
            // Funcao implementada e testada!
        }
   }
}

fun main() {
    // Teste de null, teste de bordas e de meio... Criando formações e conteudos
    val conteudoTeste0: List<ConteudoEducacional> = listOf(ConteudoEducacional("GIT-BASICO"))
    val conteudoTeste1: List<ConteudoEducacional> = listOf()
    val conteudoTeste2: List<ConteudoEducacional> = listOf(ConteudoEducacional("GIT-BASICO"),ConteudoEducacional("GIT-Intermediario"))
    val teste0 = Formacao("Java Course",Nivel.BASICO,conteudoTeste0)
    val teste1 = Formacao("Aaaa Course 2" ,Nivel.BASICO,conteudoTeste1)
    val teste2 = Formacao("BBB Course 3",Nivel.INTERMEDIARIO,conteudoTeste2)
    println(teste0)
    println(teste1)
    println(teste2)
    
    println("Teste 2: ")
	// Teste criando usuarios:
    val userteste0 = Usuario("Guilherme", 11122233344L)
    val userteste1 = Usuario("Maria", 22233344L)
    val userteste2 = Usuario("Maria", 22233344L)
    val userteste3 = Usuario("Kunka Beludo", 22233344L)

	teste0.matricular(userteste0,userteste1,userteste2)
    teste1.matricular()
    teste2.matricular(userteste1,userteste2,userteste1,userteste2,userteste3)
    
    println(teste0.inscritos)
    println(teste1.inscritos)
    println(teste2.inscritos)
	// Portanto: requisitos testado: matriculando um ou mais usuarios.
	
}

/**
 * Saida:
Formacao(nome=Java Course, nivel=BASICO, conteudos=[ConteudoEducacional(nome=GIT-BASICO, duracao=60)])
Formacao(nome=Aaaa Course 2, nivel=BASICO, conteudos=[])
Formacao(nome=BBB Course 3, nivel=INTERMEDIARIO, conteudos=[ConteudoEducacional(nome=GIT-BASICO, duracao=60), ConteudoEducacional(nome=GIT-Intermediario, duracao=60)])
Teste 2: 
[Guilherme 11122233344, Maria 22233344]
[]
[Maria 22233344]
*/