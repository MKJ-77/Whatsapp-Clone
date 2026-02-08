import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mkj.whatsapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    TopAppBar(
        title = {
            Text(
                text = "WhatsApp",
                fontSize = 21.sp,
                fontWeight = FontWeight.SemiBold
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = Color(0xFF075E54)
        ),
        actions = {

            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.camera),
                    contentDescription = "Camera",
                    modifier = Modifier.size(24.dp)
                )
            }

            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.search),
                    contentDescription = "Search",
                    modifier = Modifier.size(24.dp)
                )
            }

            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.more),
                    contentDescription = "More",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    )
}
