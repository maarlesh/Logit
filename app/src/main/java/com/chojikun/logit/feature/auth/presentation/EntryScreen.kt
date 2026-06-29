package com.chojikun.logit.feature.auth.presentation

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chojikun.logit.R

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun EntryScreen(
    modifier: Modifier = Modifier,
    onGettingStarted: () -> Unit = {},
    onSigningInTapped: () -> Unit = {},
) {
    val image = AnimatedImageVector.animatedVectorResource(R.drawable.avd_onboarding_illustration)
    var atEnd by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { atEnd = true }

    Column(
        modifier = modifier.fillMaxHeight().padding(top = 16.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                "L", modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(8.dp))
                    .padding(9.dp),
                MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                "Logit",
                modifier,
                MaterialTheme.colorScheme.surface,
                style = MaterialTheme.typography.displayLarge
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Image(
                painter = rememberAnimatedVectorPainter(image, atEnd),
                contentDescription = "Logit Illustration",
            )
        }
        Row {
            Text(
                "Your expenses, \n without the effort.",
                modifier,
                MaterialTheme.colorScheme.surface,
                style = MaterialTheme.typography.displaySmall,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 40.sp,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.background(color = MaterialTheme.colorScheme.surfaceContainer, shape = CircleShape)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text("Your data lives on your phone\n")
        }
        Row {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.background(color = MaterialTheme.colorScheme.surfaceContainer, shape = CircleShape)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text("We never ask for your bank login or password\n")
        }
        Row {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.background(color = MaterialTheme.colorScheme.surfaceContainer, shape = CircleShape)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text("We never sell your data\n")
        }
        Row {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.background(color = MaterialTheme.colorScheme.surfaceContainer, shape = CircleShape)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text("No ads. Ever.\n")
        }
        CommonButton(onClick = onGettingStarted, text = "Getting Started!")
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text("Already have an account? ")
            Text(
                "Sign in",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable { onSigningInTapped() }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun CommonButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
    ) {
        Text(text = text)
    }
}
