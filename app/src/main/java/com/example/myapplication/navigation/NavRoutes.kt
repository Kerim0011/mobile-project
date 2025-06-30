import kotlinx.serialization.Serializable
import kotlinx.serialization.InternalSerializationApi
import com.example.myapplication.data_model.Customer

@OptIn(InternalSerializationApi::class)
@Serializable
data class CarScreenRoute(val customer: Customer)