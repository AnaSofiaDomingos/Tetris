import java.io.Serializable

//@scala.serializable
class Paquets(t: String, u: String, s: Int, stg: Int) extends Serializable{

	private var usr  = u
	private var scr  = s
	private var stgm = stg
	private var titl = t

	def user: String = usr
	def score: Int   = scr
	def state: Int   = stgm
	def title: String   = titl

}
