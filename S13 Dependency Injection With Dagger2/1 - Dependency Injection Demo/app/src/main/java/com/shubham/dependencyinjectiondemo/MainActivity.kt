package com.shubham.dependencyinjectiondemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

//    private lateinit var smartPhone: SmartPhone // Now we want dagger to construct a SmartPhone and send it to the
    // main activity.

    // Changing cause of lesson 101, adding @Inject annotation.
    @Inject
    lateinit var smartPhone: SmartPhone

    // For ex lets assume we need to get a memory card instance as a dependency then all we need to do is
    @Inject
    lateinit var memoryCard: MemoryCard // declare an instance variable with @Inject annotation.

    // With the support of dagger we can get all the dependencies we want using field injection.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val battery = Battery()
        val memoryCard = MemoryCard()

        val serviceProvider = ServiceProvider()
        val simCard = SIMCard(serviceProvider)*/

        /*val smartPhoneInstance = SmartPhone(Battery(), SIMCard(ServiceProvider()), MemoryCard())
        smartPhoneInstance.makeACallWithRecording()*/ // uncomment toc check

        // Here we constructed a smartphone object injecting battery, simCard & memoryCard object as dependencies.
        // This is dependency injection, we injected dependencies through the constructor of the class.
        // This type of dependency injection is called constructor injection.

        // There are 3 types of dependency injections
        // 1] Constructor Injection
        // 2] Method Injection = Passing dependency as using a function of a class. (just like declaring dependency and assigning it value through a setter function.)
        // 3] Field Injection = Declaring dependency property(var) public and directly assigning it value from outside.
        // In android development it is recommended to use the constructor injection as much as possible.


        // With the support of Dagger2 in order to have loosely coupled, maintainable and testable code we need to get them
        // to the main activity from an outside source instead of constructing them here.

        // When you're using dagger2 for dependency injection, it is highly recommended to use constructor injection
        // for every possible scenario.
        // Now considering the instructions provided by us, dagger can generate codes to construct dependencies represented

        // To use dagger2 generated codes for dependency injection we also need an interface annotated with @Component annotation

//        DaggerSmartPhoneComponent.create().getSmartPhone().makeACallWithRecording() // Commented because of change in lesson 101

        // SO that's how we implement dependency injection using dagger 2. We didn't construct any dependency inside the MainActivity
        // instead we constructed them outside and injected them to the MainActivity with the support of Dagger2

        // When we are going to use dagger we should try to implement constructor injection for all the classes we own.
        // That means, we should go to the class and type @Inject annotation in front of the constructor like we did in the
        // previous lesson, that is the easiest and most efficient way.
        // but in some practical scenarios we are not allowed to open the class and add inject annotation to the constructor.
        // For ex: When we're using Retrofit client, since Retrofit is a third party library we build it using its builder function.
        // So in such conditions we have to use modules and write provider functions to provide those dependencies.

        // Now for the demonstration lets assume that we don't own this MemoryCard class. // Now go to MemoryCard class to read


        // *************************************************************************************************************

        // 100th lesson: Working with interfaces.

        // In some cases we may have an interface as a dependency instead of a class.
        // To learn how to work with interfaces we are changing Battery class to interface

        // *************************************************************************************************************

        // lesson 101: FieldInjection with dagger 2
//        DaggerSmartPhoneComponent.create().getSmartPhone().makeACallWithRecording()
        // We have called getSmartPhone() function of the SmartPhoneComponent interface from here, but this is not the best and most efficient way of doing it.
        // There can be many activities and fragments in the project and there can be many dependencies. Consider if we have 10 required dependency like this SmartPhone Dependency
        // We have to write the getter method for all of them in the Component interface, and we'll have to call to them from all activities in this way. this will become more difficult if  we have to pass values to those dependencies at runtime.
        // Dagger library doesn't expect us to write getter methods for dependencies like this. We can just get them injected to the activities or fragments easily using field injection.

        // Go to SmartPhoneComponent interface to learn more.

        /*DaggerSmartPhoneComponent.create().inject(this)

        smartPhone.makeACallWithRecording()*/ // commented because of lesson 102

        // *************************************************************************************************************

        // lesson 102: State of a moule

//        It is discouraged for modules to have state but for some scenarios it is a requirement, we might need to define an instance variable inside module and pass a value for it.
        // To demonstrate we are adding an int variable called memorySize to the MemoryCard module.
        // Now go to the MemoryCardModule to see

        /*DaggerSmartPhoneComponent.create().inject(this) // Now you can see because we added prameter to MemoryCardMoule this create() is showing error

        smartPhone.makeACallWithRecording()*/

        // Now we have to use builder
        // When we are constructing the component with a builder function, we have to add each module with a state here.
       /* DaggerSmartPhoneComponent.builder()
            .memoryCardModule(MemoryCardModule(100)) // look carefully, we have used simple "m" here
            .build()
            .inject(this)*/ // Commented because of lesson 103

        // This is how we pass values to a module.


        // *************************************************************************************************************

        // lesson 103: The Application Class

        // Application class is a base class of an android app. All the content of this class is predefined by the android framework.
        // Even though we cannot change this application class, we can give additional instructions to it by extending it.
        // Application class and its subclasses will be initiated before all the activities, fragments or any other application objects of an android app.
        // If there are tasks that need to run before the creation of first activity, we should create a custom application class.
        // If there are immutable data such as shared network client object or if there are global objects such as data persistence and crash reporting, that needs
        // to be shared across all components, we should create a custom application class.
        // In this Dependency Injection Demo app we have created like below component in MainActivity

        /*DaggerSmartPhoneComponent.builder()
            .memoryCardModule(MemoryCardModule(100))
            .build()
            .inject(this)*/

        // If there were 5 activities then we would have to write this code 5 times, so the recommended best practice here is writing this code part in a subclass
        // of the application class, So lets create a subclass of application class, created -> name is: SmartPhoneApplication.kt


        // Now after creating SmartPhoneApplication.kt, We'll write codes to get the application, and then get the smartPhoneComponent instance from it.
        // and finally invoke its inject function passing this activity.

        (application as SmartPhoneApplication).smartPhoneComponent
            .inject(this)

        // Now there is one special thing to do, we need to add the name of the application class we created to the Manifest.xml file

        // *************************************************************************************************************

        // lesson 104: Singletons

        // In our current project example, every time we invoke this inject function of the SmartPhoneComponent interface dagger constructs a new smartPhone
        // object and inject it to the Activity, To show you exactly what's happening we're invoking "makeACallWithRecording()" function here.

        smartPhone.makeACallWithRecording() // now you can see a new smartPhone instance has constructed
        // If we had another activity or fragment in this project which injecting the same smartphone object we would have navigated to that.
        // We can also create one if we want, check by rotating the device screen and look at logcat, dagger will construct another smartPhone object and inject
        // on each recreation of activity, and every time it get injected dagger constructs a new smartphone object in the memory
        // not only that, in order to do so, dagger always have to construct ServiceProvider, SIMCard, MemoryCard and NickelCadmiumBattery objects as well.
        // this is very inefficient.
        // We can easily avoid this using @Singleton annotation to the SmartPhone.kt Class.
        // We also have to annotate the interface of SmartPhone which is SmartPhoneComponent.kt

        // Now run the app and check the logs by rotating the device.
        // This time instead of constructing a new smartphone instance on recreation, app has just used the existing singleton smartphone instance to invoke the makeACallWithRecording function.

        // So this is how we can use singleton annotation with dagger.


    }



}















