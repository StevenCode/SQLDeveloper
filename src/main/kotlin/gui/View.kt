package gui

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class View : Application() {
    override fun start(primaryStage: Stage?) {
        val parent = FXMLLoader.load<Parent>(View::class.java.getResource("/activity_main.fxml"))

        val theScene = Scene(parent, 800.0, 600.0)
        primaryStage?.run { scene = theScene
        title ="SQL Developer"
        isResizable = false
            show()
        }
    }

    fun onCreate() = launch(View::class.java)
}

fun main(args: Array<String>) = View().onCreate()