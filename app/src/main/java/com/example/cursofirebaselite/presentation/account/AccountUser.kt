package com.example.cursofirebaselite.presentation.account

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cursofirebaselite.presentation.home.Screen
import com.example.cursofirebaselite.ui.theme.Black
import com.example.cursofirebaselite.ui.theme.colorPlaylist
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountUser(auth: FirebaseAuth) {
    val currentUser = auth.currentUser
    val db = FirebaseFirestore.getInstance()
    val context = LocalContext.current

    var name by remember { mutableStateOf("Loading...") }
    var email by remember { mutableStateOf("Loading...") }
    var photoUrl by remember { mutableStateOf("Loading...") }
    var emailVerified by remember { mutableStateOf(false) }
    var uid by remember { mutableStateOf("Loading...") }

    if (currentUser != null) {
        db.collection("users").document(currentUser.uid)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    name = document.getString("name") ?: "No Name"
                    email = document.getString("email") ?: "No Email"
                    photoUrl = document.getString("photoUrl") ?: "No Photo URL"
                    emailVerified = document.getBoolean("emailVerified") ?: false
                    uid = document.getString("uid") ?: "No UID"
                } else {
                    Log.d("Firestore", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Firestore", "get failed with ", exception)
            }

        Screen {
            val color = Color(0xFF696969)
            val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
            ) { padding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Black)
                        .padding(padding)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(10.dp),
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp),
                        ) {
                            AsyncImage(
                                model = photoUrl,
                                contentDescription = "user photo",
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                name,
                                modifier = Modifier.align(Alignment.CenterVertically),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color)
                        )
                        Spacer(modifier = Modifier.padding(15.dp))
                        Text("Datos de la cuenta", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.White)
                        Spacer(modifier = Modifier.height(15.dp))
                        Column {
                            Text("Nombre de usuario", fontWeight = FontWeight.Bold, fontSize = 16.sp,color = Color.White)
                            Row {
                                Text(text = uid, fontSize = 15.sp, color = colorPlaylist)
                                Spacer(modifier = Modifier.weight(1f))
                                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "acc")
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text("Correo electrónico", fontWeight = FontWeight.Bold, fontSize = 16.sp,color = Color.White)
                        Text(text = email, fontSize = 15.sp, color = colorPlaylist)
                        if (!emailVerified) {
                            Row {
                                Text("Correo no verificado", color = Color.Red,fontSize = 13.sp)
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    "Verificar correo",
                                    textDecoration = TextDecoration.Underline,
                                    fontFamily = FontFamily.SansSerif,
                                    color = colorPlaylist,
                                    fontSize = 13.sp,
                                    modifier = Modifier.clickable {
                                        currentUser.sendEmailVerification()
                                    }
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Icon(imageVector = Icons.Default.Email, contentDescription = "email")
                            }
                        } else {
                            Row {
                                Text("Correo verificado", color = Color.Green)
                                Spacer(modifier = Modifier.weight(1f))
                                Icon(imageVector = Icons.Default.Email, contentDescription = "email")
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color)
                        )
                        Spacer(modifier = Modifier.height(25.dp))
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Button(
                                onClick = {
                                    //auth.signOut()
                                    Toast.makeText(context, "PENDIENTE DE HACER", Toast.LENGTH_SHORT).show()
                                },
                                modifier = Modifier,
                                colors = ButtonDefaults.buttonColors(Color.White)
                            ) {
                                Text("Cerrar sesión")
                            }
                        }


                    }
                }
            }
        }
    } else {
        // Handle case when no user is signed in
    }
}


//fun saveUserDetailsToFirestore(user: FirebaseUser) {
//    val db = FirebaseFirestore.getInstance()
//    val userMap = hashMapOf(
//        "name" to (user.displayName ?: "No Name"),
//        "email" to user.email,
//        "photoUrl" to (user.photoUrl?.toString() ?: ""),
//        "emailVerified" to user.isEmailVerified,
//        "uid" to user.uid
//    )
//
//    db.collection("users")
//        .document(user.uid)
//        .set(userMap)
//        .addOnSuccessListener {
//            Log.d("Firestore", "User details successfully written!")
//        }
//        .addOnFailureListener { e ->
//            Log.w("Firestore", "Error writing user details", e)
//        }
//}