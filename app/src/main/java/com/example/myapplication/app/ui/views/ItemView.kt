package com.example.myapplication.app.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplication.data.local.entity.MenuItemEntity

@Composable
fun MenuItem(item: MenuItemEntity) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            // Category Header with Gradient Background
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.onPrimary,
                                MaterialTheme.colorScheme.primary,
                            )
                        )
                    )
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Text(
                    text = item.category.uppercase(),
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            }

            // Main Content
            Column(modifier = Modifier.padding(16.dp)) {
                Row {
                    // Image Section with Overlay for Unavailable Items
                    Box {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(item.image)
                                .crossfade(true)
                                .build(),
                            contentDescription = item.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(100.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .shadow(4.dp, RoundedCornerShape(16.dp))
                        )

                        // Unavailable Overlay
                        if (!item.available) {
                            Box(
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(Color.Black.copy(alpha = 0.6f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "OUT OF\nSTOCK",
                                    color = Color.White,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    // Content Column
                    Column(modifier = Modifier.weight(1f)) {
                        // Name and Price Row
                        Row(
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = item.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = Color.Black,
                                modifier = Modifier.weight(1f),
                                lineHeight = 22.sp
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "$${String.format("%.2f", item.price)}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = Color(0xFF16A085)
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Description
                        Text(
                            text = item.description,
                            fontSize = 14.sp,
                            color = Color(0xFF6B7280),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            lineHeight = 18.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Tags Row
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            // Rating Badge
                            item {
                                Surface(
                                    shape = RoundedCornerShape(20.dp),
                                    color = Color(0xFFFEF3C7),
                                    modifier = Modifier.wrapContentSize()
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Star,
                                            contentDescription = null,
                                            tint = Color(0xFFF59E0B),
                                            modifier = Modifier.size(14.dp)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = "${item.rating}",
                                            fontSize = 12.sp,
                                            color = Color(0xFFD97706),
                                            fontWeight = FontWeight.Medium
                                        )
                                    }
                                }
                            }

                            // Vegan Badge
                            if (item.vegan) {
                                item {
                                    Surface(
                                        shape = RoundedCornerShape(20.dp),
                                        color = Color(0xFFDCFCE7),
                                        modifier = Modifier.wrapContentSize()
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                                        ) {
                                            Text(
                                                text = "🌱",
                                                fontSize = 12.sp
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text(
                                                text = "VEGAN",
                                                fontSize = 11.sp,
                                                color = Color(0xFF16A34A),
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                }
                            }

                            // Hot Badge
                            if (item.hot){
                                item {
                                    Surface(
                                        shape = RoundedCornerShape(20.dp),
                                        color =  Color(0xFFFEE2E2) ,
                                        modifier = Modifier.wrapContentSize()
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                                        ) {
                                            Text(
                                                text = "🌶️",
                                                fontSize = 12.sp
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text(
                                                text = "HOT" ,
                                                fontSize = 11.sp,
                                                color =Color(0xFFDC2626),
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                }

                            }

                            // Unavailable Badge
                            if (!item.available) {
                                item {
                                    Surface(
                                        shape = RoundedCornerShape(20.dp),
                                        color = Color(0xFFFEE2E2),
                                        modifier = Modifier.wrapContentSize()
                                    ) {
                                        Text(
                                            text = "UNAVAILABLE",
                                            fontSize = 11.sp,
                                            color = Color(0xFFDC2626),
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun MenuItemPreview() {
    Column {
        MenuItem(
            MenuItemEntity(
                id = 401,
                name = "Chicken Club Sandwich",
                price = 7.95,
                description = "Grilled chicken with bacon, lettuce, tomato, and mayo",
                vegan = true,
                hot = true,
                rating = 4.3,
                image = "https://example.com/images/classic-beef-burger.jpg",
                available = true,
                category = "Pasta"
            )
        )

    }

}




